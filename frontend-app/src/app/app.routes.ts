import { Routes } from '@angular/router';
import { KeynotesComponent } from './components/keynotes/keynotes.component';
import { ConferencesComponent } from './components/conferences/conferences.component';

export const routes: Routes = [
    { path: 'keynotes', component: KeynotesComponent },
    { path: 'conferences', component: ConferencesComponent },
    { path: '', redirectTo: '/conferences', pathMatch: 'full' }
];
