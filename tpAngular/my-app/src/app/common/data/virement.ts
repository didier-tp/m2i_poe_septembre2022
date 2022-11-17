//cette classe pourrait être appelée VirementDto
export class Virement{
    constructor(public montant :number = 0,
                public numCptDeb :number =0,
                public numCptCred :number = 0,
                public ok : boolean | undefined = undefined,
                public message : string | undefined = undefined){}
}