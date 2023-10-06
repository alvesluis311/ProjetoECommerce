import { Injectable } from '@angular/core';
import { HttpClient} from '@angular/common/http';
import { Observable } from 'rxjs';
import { Plataforma } from '../models/plataforma.model';

@Injectable({
  providedIn: 'root'
})
export class PlataformaService {
  private baseURL: string =  'http://localhost:8080';

  constructor(private http: HttpClient) {}

  findAll(): Observable<Plataforma[]> {
    return this.http.get<Plataforma[]>(`${this.baseURL}/plataformas`);
  }

  findById(id: string): Observable<Plataforma> {
    return this.http.get<Plataforma>(`${this.baseURL}/plataformas/${id}`);
  }

  save(plataforma: Plataforma): Observable<Plataforma> {
    return this.http.post<Plataforma>(`${this.baseURL}/plataformas`, plataforma);
  }

  update(plataforma: Plataforma): Observable<Plataforma> {
    return this.http.put<Plataforma>(`${this.baseURL}/plataformas/${plataforma.id}`, plataforma);
  }

  delete(plataforma: Plataforma): Observable<any> {
    return this.http.delete<any>(`${this.baseURL}/plataformas/${plataforma.id}`);
  }
}
