import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

const routes: Routes = [
  {path: 'estados', loadChildren: () => import('./estado/estado.module').then(m => m.EstadoModule)},
  {path: 'games', loadChildren: () => import('./game/game.module').then(m => m.GameModule)},
  {path: 'generos', loadChildren: () => import('./genero/genero.module').then(m => m.GeneroModule)},
  {path: 'developers', loadChildren: () => import('./developer/developer.module').then(m => m.DeveloperModule)}

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
