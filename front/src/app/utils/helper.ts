export interface ItemEnum {
  id: number;
  descricao: string;
}

export interface Enumerador{
  [key: string]: ItemEnum;
}

export abstract class Enumeradores {

  public static factory<T extends keyof typeof Enumeradores>(tipo: T) {

    const enumerador = this[tipo] as Enumerador;

    return {
      getDescricao: (id: number): string => {
        let desc: string = "";
        for(const key in enumerador) {
          if(enumerador.hasOwnProperty(key)) {
            if(enumerador[key].id === id) {
              desc = enumerador[key].descricao;
              // return enumerador[key].descricao;
            }
          }
        }
        // return undefined;
        return desc;
      }
    }
  }

  public static readonly Booleano: Enumerador = {
    NAO: { id: 0, descricao: 'Não' },
    SIM: { id: 1, descricao: 'Sim' }
  };

  public static readonly TipoMovVeiculo: Enumerador = {
    HORA: { id: 18, descricao: 'Por Hora' },
    DIA: { id: 8, descricao: 'Diário' },
    FINAL_SEMANA: { id: 9, descricao: 'Final de Semana' },
    MENSALISTA: { id: 10, descricao: 'Mensalista' },
    INDIFERENTE: { id: 11, descricao: 'Indiferente' }
  };

  public static readonly SituacaoMovimento: Enumerador = {
    ABERTO: { id: 12, descricao: 'Aberto' },
    ENCERRADO: { id: 13, descricao: 'Encerrado'}
  };
}

export abstract class Utils {
  public static formatarPlaca(placa: string): string {

    let x = placa.toUpperCase().replaceAll(/[^a-zA-Z0-9]/g, '');
    console.log(x);
    return x;
  }
}
