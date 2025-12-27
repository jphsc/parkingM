import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, Validators } from '@angular/forms';
import { pairwise, startWith } from 'rxjs';
import { Utils } from 'src/app/utils/util';

@Component({
  selector: 'app-veiculo-form',
  templateUrl: './veiculo-form.component.html',
  styleUrls: ['./veiculo-form.component.css']
})
export class VeiculoFormComponent implements OnInit {

  constructor(private fb: FormBuilder) { }

  ngOnInit(): void {
    this.formVeiculo.get("placa")?.valueChanges
    .subscribe(placa => {
      if(!placa) return;

      const placaFormatada = Utils.formatarPlaca(placa);
      this.formVeiculo.get("placa")?.setValue(placaFormatada, { emitEvent: false });
    });
  }

  formVeiculo = this.fb.group({
    id: new FormControl<number | null>(null),
    montadora: new FormControl<string | null>('1', [Validators.required, Validators.minLength(2)]),
    modelo: new FormControl<string | null>('1', [Validators.required, Validators.minLength(2)]),
    placa: new FormControl<string | null>('1', [Validators.required, Validators.minLength(7)]),
  });

  submeter(): void {
    console.log(this.formVeiculo.value);
  }
}
