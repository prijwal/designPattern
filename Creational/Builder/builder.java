class Main {

    public class OAuthRequestBuilder {
        private String clientId;
        private String redirectUri;
        private String scope;
        private String state;
        private String responseType = "code";
        private String authUrl = "https://okta-848175/oauth2/default/v1/authorize"; 
    
        public OAuthRequestBuilder setClientId(String clientId) {
            this.clientId = clientId;
            return this;
        }
    
        public OAuthRequestBuilder setRedirectUri(String redirectUri) {
            this.redirectUri = redirectUri;
            return this;
        }
    
        public OAuthRequestBuilder setScope(String scope) {
            this.scope = scope;
            return this;
        }
    
        public OAuthRequestBuilder setState(String state) {
            this.state = state;
            return this;
        }
    
        public String build() {
            if (clientId == null || redirectUri == null) {
                throw new IllegalArgumentException("Client ID and Redirect URI must be set.");
            }
    
            StringBuilder url = new StringBuilder(authUrl);
            url.append("?response_type=").append(responseType);
            url.append("&client_id=").append(clientId);
            url.append("&redirect_uri=").append(redirectUri);
    
            if (scope != null && !scope.isEmpty()) {
                url.append("&scope=").append(scope);
            }
    
            if (state != null && !state.isEmpty()) {
                url.append("&state=").append(state);
            }
    
            return url.toString();
        }
    }

    public static void main(String[] args) {
        Main mainInstance = new Main();  
        OAuthRequestBuilder builder = mainInstance.new OAuthRequestBuilder(); 
        String oauthUrl = builder
                .setClientId("fasa-a4afar4-afrafs") 
                .setRedirectUri("https://miniorange/callback")  
                .setScope("openid profile email")
                .setState("xyz123")
                .build();

        System.out.println("OAuth URL is: " + oauthUrl);
    }
}

