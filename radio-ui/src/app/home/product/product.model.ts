export class Product {
    constructor(
        public id: number,
        public name: string,
        public description: string,
    ) {}
}

export class SingleProduct extends Product {

    constructor(
        public id: number,
        public name: string,
        public description: string,
        public amount: number
    ) {
        super(id, name, description);
    }
}

export class ComposedProduct extends Product {

    constructor(
        public id: number,
        public name: string,
        public description: string,
        public prods: {},
        public virtualAmount?: number,
    ) {
        super(id, name, description);
    }
}
