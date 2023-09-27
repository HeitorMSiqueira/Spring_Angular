export interface Tarefa {
    _id: string;
    titulo: string;
    descricao: string;
    dataHoraCriacao: string
    dataHoraConclusao: string;
    excluido: boolean;
    status: string;
}