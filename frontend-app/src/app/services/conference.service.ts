import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Conference, Review } from '../models/conference.model';

@Injectable({
    providedIn: 'root'
})
export class ConferenceService {
    private apiUrl = 'http://localhost:8080/conferences/api/conferences';

    constructor(private http: HttpClient) { }

    getAllConferences(): Observable<Conference[]> {
        return this.http.get<Conference[]>(this.apiUrl);
    }

    getConference(id: number): Observable<Conference> {
        return this.http.get<Conference>(`${this.apiUrl}/${id}`);
    }

    saveConference(conference: Conference): Observable<Conference> {
        return this.http.post<Conference>(this.apiUrl, conference);
    }

    addReview(conferenceId: number, review: Review): Observable<Review> {
        return this.http.post<Review>(`${this.apiUrl}/${conferenceId}/reviews`, review);
    }
}
