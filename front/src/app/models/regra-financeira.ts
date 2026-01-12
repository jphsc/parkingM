export interface RegraFinanceira {
  id: number,
  descricao: string,
  valor: number,
  tipoCobranca: any,
  tipoMovimento: any,
  dtHrInicioValidade: Date,
  dtHrFimValidade?: Date,
  situacao: any,
  versao: Date
}
