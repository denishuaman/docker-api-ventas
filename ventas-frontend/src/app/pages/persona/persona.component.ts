import { Component, OnInit, ViewChild } from '@angular/core';
import { MatTableDataSource, MatPaginator, MatSort, MatDialog, MatSnackBar } from '@angular/material';
import { Persona } from 'src/app/_model/persona';
import { PersonaService } from 'src/app/_service/persona.service';
import { PersonaDialogoComponent } from './persona-dialogo/persona-dialogo.component';
import { switchMap } from 'rxjs/operators';

@Component({
  selector: 'app-persona',
  templateUrl: './persona.component.html',
  styleUrls: ['./persona.component.css']
})
export class PersonaComponent implements OnInit {

  nombresColumnas = ['idPersona', 'dni', 'nombres', 'apellidos', 'acciones'];
  dataSource: MatTableDataSource<Persona>;

  @ViewChild(MatPaginator, { static: true })
  paginator: MatPaginator;

  @ViewChild(MatSort, { static: true })
  sort: MatSort;

  constructor(private personaService: PersonaService, private dialogo: MatDialog,
    private snackBar: MatSnackBar) { }

  ngOnInit() {

    this.personaService.cambioPersona.subscribe(personas => {
      this.dataSource = new MatTableDataSource(personas);
      this.dataSource.paginator = this.paginator;
      this.dataSource.sort = this.sort;
    });

    this.personaService.mensaje.subscribe(detalle => {
      this.snackBar.open(detalle, 'Aviso', { duration: 5000 });
    });

    //listando las personas
    this.personaService.listar().subscribe(personas => {
      this.dataSource = new MatTableDataSource(personas);
      this.dataSource.paginator = this.paginator;
      this.dataSource.sort = this.sort;
    })
  }

  filtrar(valor: string) {
    this.dataSource.filter = valor.trim().toLowerCase();
  }

  abrirDialogo(dni?: string) {
    // let p = persona != null ? persona : new Persona();
    if (dni) {
      this.personaService.buscarPorDni(dni).subscribe(dataPersona => {
        this.dialogo.open(PersonaDialogoComponent, {
          width: '300px',
          data: dataPersona
        });
      });
    } else {
      let persona = new Persona();
      console.log('data de persona', persona);
      this.dialogo.open(PersonaDialogoComponent, {
        width: '300px',
        data: persona
      });
    }
  }

  eliminar(persona: Persona) {
    this.personaService.eliminar(persona.dni).pipe(switchMap(() => {
      return this.personaService.listar();
    })).subscribe(personasRegistradas => {
      this.personaService.cambioPersona.next(personasRegistradas);
      this.personaService.mensaje.next('Se eliminó a \"' + persona.nombres.concat(' ').concat(persona.apellidos) + '\" del registro de personas');
    }, error => {
      this.personaService.mensaje.next('NO se puede eliminar a \"'+persona.nombres.concat(' ').concat(persona.apellidos)+'. Es posible que esté registrado en una venta')
    });
  }

}
