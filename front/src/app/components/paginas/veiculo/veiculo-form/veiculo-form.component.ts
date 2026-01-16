import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, Validators } from '@angular/forms';
import { Veiculo } from 'src/app/models/veiculo.model';
import { VeiculoService } from 'src/app/services/veiculo.service';
import { Utils } from 'src/app/utils/util';

@Component({
  selector: 'app-veiculo-form',
  templateUrl: './veiculo-form.component.html',
  styleUrls: ['./veiculo-form.component.css']
})
export class VeiculoFormComponent implements OnInit {

  formVeiculo = this.fb.group({
    id: new FormControl<number | null>(null),
    montadora: new FormControl<string>('Chevrolet', [Validators.required, Validators.minLength(3)]),
    modelo: new FormControl<string>('Onix', [Validators.required, Validators.minLength(3)]),
    placa: new FormControl<string>('ECR5B01', [Validators.required, Validators.minLength(7)]),
  });

  constructor(private fb: FormBuilder, private vs: VeiculoService) { }

  ngOnInit(): void {
    this.formVeiculo.get("placa")?.valueChanges
    .subscribe(placa => {
      if(!placa) return;

      const placaFormatada = Utils.formatarPlaca(placa);
      this.formVeiculo.get("placa")?.setValue(placaFormatada, { emitEvent: false });
    });
  }

  async criarVeiculo(): Promise<void> {
    let veiculo: Veiculo = {
      placa: this.formVeiculo.value.placa!,
      modelo: this.formVeiculo.value.modelo!,
      montadora: this.formVeiculo.value.montadora!,
      dtRegistro: new Date().toISOString(),
      versao: new Date().toISOString()
    };

    this.vs.createVeiculo(veiculo).subscribe({
      next: (resp) => {
        resp.registros[0];
        console.log('Veículo criado com sucesso');
        console.log(resp.registros[0]);
      },
      error: (err) => {
        alert(err.error.mensagem);
        console.error('Erro ao criar veículo:', err);
      }
    });
  }
}
