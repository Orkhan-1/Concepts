class Stateless {

    // Login Controller - Creates token but stores NOTHING
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {

        if (userService.isValid(loginRequest)) {
            // Create a JWT token with user ID and roles in its payload (stateless)
            String token = Jwts.builder()
                    .setSubject(loginRequest.getUsername()) // Put user ID in the token
                    .claim("roles", "USER") // Put roles in the token
                    .setExpiration(new Date(System.currentTimeMillis() + 864000000)) // 10 days
                    .signWith(SignatureAlgorithm.HS512, secretKey) // Sign it
                    .compact();

            return ResponseEntity.ok(new AuthResponse(token)); // Send token to client
        }
        return ResponseEntity.status(401).build();
    }

    // Profile Controller - Validates token without any server state
    @GetMapping("/profile")
    public ResponseEntity<?> profile(@RequestHeader("Authorization") String authHeader) {

        try {
            String token = authHeader.replace("Bearer ", "");
            // Verify the signature and parse the claims FROM THE TOEN ITSELF. No DB call.
            Claims claims = Jwts.parser()
                    .setSigningKey(secretKey)
                    .parseClaimsJws(token)
                    .getBody();

            String username = claims.getSubject(); // Get user ID from the token's payload
            User user = userService.findUser(username); // Now fetch user details from DB

            return ResponseEntity.ok(user);
        } catch (Exception e) {
            return ResponseEntity.status(401).build(); // Token is invalid
        }
    }

}