export class Order{
    constructor(
        public id: number,
        public status: string,
        public customer: string,
        public creationDate: string,
        public deliveryDate: string,
        public prods: {}
    ) {}
}


/*
private Long id;
    private String status;
    private String creationDate;
    private String deliveryDate;
    private String customer;
    private HashMap<Long, Integer> prods = new HashMap<>();
    */