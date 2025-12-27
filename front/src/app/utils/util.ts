export abstract class Utils {
  public static formatarPlaca(placa: string):string {
    placa = placa.replaceAll(/[^a-zA-Z0-9]/g, '').toUpperCase().substring(0,7);

    console.log(placa);

    return placa;
  }

  public static uppercaseOnly(valor: string): string {
    return valor.toUpperCase();
  }
}
