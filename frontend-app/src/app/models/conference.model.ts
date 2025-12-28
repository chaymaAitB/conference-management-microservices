export interface Review {
    id?: number;
    date: string;
    text: string;
    stars: number;
}

export interface Conference {
    id?: number;
    titre: string;
    type: string;
    date: string;
    duree: number;
    inscrits: number;
    score: number;
    keynoteId?: number;
    reviews?: Review[];
}
