@RestController
public class ShoppingCartController {

    @PostMapping("/login")
    public Response login(@RequestBody User user, HttpServletRequest request) {
        // ... validate user ...
        HttpSession session = request.getSession(true); // Create a new session
        session.setAttribute("username", user.getUsername());
        session.setAttribute("cart", new ArrayList<>()); // Create a cart IN the session
        return new Response("Login successful", session.getId());
    }

    @PostMapping("/cart/add")
    public Response addToCart(@RequestParam String itemId, HttpServletRequest request) {
        HttpSession session = request.getSession(false); // Retrieve existing session
        if (session == null) {
            throw new RuntimeException("Not logged in!");
        }

        // Get the cart FROM the user's specific session
        List<String> cart = (List<String>) session.getAttribute("cart");
        cart.add(itemId); // Modify the state stored in the session

        return new Response("Item added", cart);
    }
}