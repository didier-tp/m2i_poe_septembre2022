//cette classe pourrait être appelée ClientDto
export class Client{
    constructor(public number :number = 0,
                public firstName :string ="",
                public lastName :string = "",
                public address :string = "",
                public email :string = ""){}
}