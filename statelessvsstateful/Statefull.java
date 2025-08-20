class Statefull {

    // Login Controller
    @PostMapping("/login")
    public String login(@RequestParam String username,
                        @RequestParam String password,
                        HttpServletRequest request) {

        if (userService.isValid(username, password)) {
            HttpSession session = request.getSession(); // Creates a session
            session.setAttribute("userId", username);   // Store state on the server
            return "dashboard";
        }
        return "login-failed";
    }

    // Profile Controller - Needs the state to work
    @GetMapping("/profile")
    public String profile(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            String userId = (String) session.getAttribute("userId"); // Retrieve state from server
            User user = userService.findUser(userId);
            return "profile-page";
        }
        return "redirect:/login";
    }

}