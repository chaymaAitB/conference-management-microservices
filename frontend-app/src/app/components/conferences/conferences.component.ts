import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ConferenceService } from '../../services/conference.service';
import { Conference, Review } from '../../models/conference.model';
import { FormsModule } from '@angular/forms';

@Component({
    selector: 'app-conferences',
    standalone: true,
    imports: [CommonModule, FormsModule],
    template: `
    <div class="container mx-auto p-4">
      <h2 class="text-2xl font-bold mb-4">Conferences Management</h2>
      
      <div class="mb-4 p-4 border rounded shadow">
        <h3 class="text-xl mb-2">Create Conference</h3>
        <input [(ngModel)]="newConference.titre" placeholder="Titre" class="border p-2 mr-2" />
        <select [(ngModel)]="newConference.type" class="border p-2 mr-2">
          <option value="ACADEMIQUE">Académique</option>
          <option value="COMMERCIALE">Commerciale</option>
        </select>
        <input type="date" [(ngModel)]="newConference.date" class="border p-2 mr-2" />
        <input type="number" [(ngModel)]="newConference.duree" placeholder="Durée" class="border p-2 mr-2" />
        <input type="number" [(ngModel)]="newConference.keynoteId" placeholder="Keynote ID" class="border p-2 mr-2" />
        <button (click)="saveConference()" class="bg-green-500 text-white p-2 rounded">Create</button>
      </div>

      <div class="grid grid-cols-1 gap-4">
        <div *ngFor="let c of conferences" class="p-4 border rounded shadow hover:shadow-lg bg-white">
          <div class="flex justify-between items-start">
            <div>
              <h3 class="font-bold text-xl">{{c.titre}}</h3>
              <span class="bg-gray-200 px-2 py-1 rounded text-sm">{{c.type}}</span>
              <p class="text-gray-600">Date: {{c.date}} | Duration: {{c.duree}}h</p>
              <p>Keynote ID: {{c.keynoteId}}</p>
            </div>
            <div class="text-right">
              <p class="font-bold text-yellow-500">Score: {{c.score}}</p>
              <p class="text-sm text-gray-500">{{c.inscrits}} inscrits</p>
            </div>
          </div>

          <div class="mt-4 border-t pt-2">
            <h4 class="font-bold mb-2">Reviews</h4>
            <div *ngFor="let r of c.reviews" class="bg-gray-50 p-2 mb-2 rounded">
              <div class="flex justify-between">
                <span class="text-yellow-500">{{'★'.repeat(r.stars)}}</span>
                <span class="text-xs text-gray-500">{{r.date}}</span>
              </div>
              <p>{{r.text}}</p>
            </div>
            
            <div class="mt-2 flex gap-2">
              <input [(ngModel)]="newReview.text" placeholder="Review text" class="border p-1 flex-grow" />
              <input type="number" [(ngModel)]="newReview.stars" min="1" max="5" class="border p-1 w-16" />
              <button (click)="addReview(c.id!)" class="bg-blue-500 text-white px-2 rounded">Add Review</button>
            </div>
          </div>
        </div>
      </div>
    </div>
  `
})
export class ConferencesComponent implements OnInit {
    conferences: Conference[] = [];
    newConference: Conference = {
        titre: '', type: 'ACADEMIQUE', date: '', duree: 0, inscrits: 0, score: 0
    };
    newReview: Review = { date: new Date().toISOString().split('T')[0], text: '', stars: 5 };

    constructor(private conferenceService: ConferenceService) { }

    ngOnInit(): void {
        this.loadConferences();
    }

    loadConferences() {
        this.conferenceService.getAllConferences().subscribe(data => this.conferences = data);
    }

    saveConference() {
        this.conferenceService.saveConference(this.newConference).subscribe(() => {
            this.loadConferences();
            this.newConference = { titre: '', type: 'ACADEMIQUE', date: '', duree: 0, inscrits: 0, score: 0 };
        });
    }

    addReview(conferenceId: number) {
        this.conferenceService.addReview(conferenceId, this.newReview).subscribe(() => {
            this.loadConferences();
            this.newReview = { date: new Date().toISOString().split('T')[0], text: '', stars: 5 };
        });
    }
}
