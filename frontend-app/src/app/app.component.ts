import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterOutlet, RouterLink } from '@angular/router';
import { OAuthService, NullValidationHandler } from 'angular-oauth2-oidc';
import { authConfig } from './auth.config';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [CommonModule, RouterOutlet, RouterLink],
  template: `
    <div class="min-h-screen bg-gray-100">
      <nav class="bg-blue-600 text-white p-4">
        <div class="container mx-auto flex justify-between items-center">
          <h1 class="text-xl font-bold">Conference App</h1>
          <div *ngIf="oauthService.hasValidAccessToken()">
            <ul class="flex gap-4">
              <li><a routerLink="/keynotes" class="hover:text-blue-200">Keynotes</a></li>
              <li><a routerLink="/conferences" class="hover:text-blue-200">Conferences</a></li>
              <li><button (click)="logout()" class="text-red-200 hover:text-white">Logout</button></li>
            </ul>
          </div>
          <div *ngIf="!oauthService.hasValidAccessToken()">
            <button (click)="login()" class="bg-white text-blue-600 px-4 py-1 rounded">Login</button>
          </div>
        </div>
      </nav>
      <main class="p-4">
        <div *ngIf="oauthService.hasValidAccessToken()">
            <router-outlet></router-outlet>
        </div>
        <div *ngIf="!oauthService.hasValidAccessToken()" class="text-center mt-10">
            <h2 class="text-2xl">Please log in to manage conferences.</h2>
        </div>
      </main>
    </div>
  `,
  styles: []
})
export class AppComponent {
  title = 'frontend-app';

  constructor(public oauthService: OAuthService) {
    this.configure();
  }

  configure() {
    this.oauthService.configure(authConfig);
    this.oauthService.tokenValidationHandler = new NullValidationHandler();
    this.oauthService.loadDiscoveryDocumentAndTryLogin();
  }

  login() {
    this.oauthService.initLoginFlow();
  }

  logout() {
    this.oauthService.logOut();
  }
}
