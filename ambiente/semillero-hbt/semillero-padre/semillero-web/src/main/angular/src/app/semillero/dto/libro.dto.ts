

export class LibroDTO{

    /*constructor(id:number,nombre:string,editorial:string,temtica:string,numeroPaginas:number,
        precio:number,autores:string,fechaVenta:Date,estado:string,color:any){}*/
    //indica el Identificador del libro
    public id: number;

    //indica el Nombre del libro
    public nombre: string;

    //indica la Editorial del libro
    public editorial: string;

    //indica la Tematica del libro
    public tematica: string;

    //indica la Paginas del libro
    public numeroPaginas:number;

    //indica el Precio del libro
    public precio:number;

    //indica el Nombre de los autores del libro
    public autores:string;

    //indica la fecha de venta del libro
    public fechaVenta:Date;

    //indica el Estado del libro
    public estado:string;

    //indica el Color del libro
    public color:boolean;

}