import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { KeynoteService } from '../../services/keynote.service';
import { Keynote } from '../../models/keynote.model';
import { FormsModule } from '@angular/forms';

@Component({
    selector: 'app-keynotes',
    standalone: true,
    imports: [CommonModule, FormsModule],
    template: `
    <div class="container mx-auto p-4">
      <h2 class="text-2xl font-bold mb-4">Keynotes Management</h2>
      
      <div class="mb-4 p-4 border rounded shadow">
        <h3 class="text-xl mb-2">Add/Edit Keynote</h3>
        <input [(ngModel)]="currentKeynote.nom" placeholder="Nom" class="border p-2 mr-2" />
        <input [(ngModel)]="currentKeynote.prenom" placeholder="Prenom" class="border p-2 mr-2" />
        <input [(ngModel)]="currentKeynote.email" placeholder="Email" class="border p-2 mr-2" />
        <input [(ngModel)]="currentKeynote.fonction" placeholder="Fonction" class="border p-2 mr-2" />
        <button (click)="saveKeynote()" class="bg-blue-500 text-white p-2 rounded">Save</button>
      </div>

      <div class="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-4">
        <div *ngFor="let k of keynotes" class="p-4 border rounded shadow hover:shadow-lg">
          <h3 class="font-bold">{{k.nom}} {{k.prenom}}</h3>
          <p>{{k.email}}</p>
          <p class="text-gray-600">{{k.fonction}}</p>
          <div class="mt-2">
            <button (click)="editKeynote(k)" class="text-blue-500 mr-2">Edit</button>
            <button (click)="deleteKeynote(k.id!)" class="text-red-500">Delete</button>
          </div>
        </div>
      </div>
    </div>
  `
})
export class KeynotesComponent implements OnInit {
    keynotes: Keynote[] = [];
    currentKeynote: Keynote = { nom: '', prenom: '', email: '', fonction: '' };

    constructor(private keynoteService: KeynoteService) { }

    ngOnInit(): void {
        this.loadKeynotes();
    }

    loadKeynotes() {
        this.keynoteService.getAllKeynotes().subscribe(data => this.keynotes = data);
    }

    saveKeynote() {
        if (this.currentKeynote.id) {
            this.keynoteService.updateKeynote(this.currentKeynote.id, this.currentKeynote).subscribe(() => {
                this.loadKeynotes();
                this.resetForm();
            });
        } else {
            this.keynoteService.saveKeynote(this.currentKeynote).subscribe(() => {
                this.loadKeynotes();
                this.resetForm();
            });
        }
    }

    editKeynote(keynote: Keynote) {
        this.currentKeynote = { ...keynote };
    }

    deleteKeynote(id: number) {
        this.keynoteService.deleteKeynote(id).subscribe(() => this.loadKeynotes());
    }

    resetForm() {
        this.currentKeynote = { nom: '', prenom: '', email: '', fonction: '' };
    }
}
