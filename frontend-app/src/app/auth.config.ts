import { AuthConfig } from 'angular-oauth2-oidc';

export const authConfig: AuthConfig = {
    issuer: 'http://localhost:8180/realms/conference-realm',
    redirectUri: window.location.origin + '/index.html',
    clientId: 'conference-frontend',
    scope: 'openid profile email',
    responseType: 'code',
    showDebugInformation: true,
    strictDiscoveryDocumentValidation: false
};
