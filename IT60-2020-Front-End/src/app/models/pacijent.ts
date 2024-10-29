import { Dijagnoza } from "./dijagnoza";
import { Odeljenje } from "./odeljenje";

export class Pacijent {
    id!: number;
    ime!: string;
    prezime!: string;
    zdr_osiguranje!: boolean;
    datum_rodjenja!: Date;
    dijagnoza!: Dijagnoza;
    odeljenje!: Odeljenje;
}