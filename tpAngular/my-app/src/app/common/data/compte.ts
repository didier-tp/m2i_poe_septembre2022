//cette classe pourrait être appelée CompteDto
export class Compte{
    constructor(public numero :number = 0,
                public label :string ="",
                public solde :number = 0){}
}