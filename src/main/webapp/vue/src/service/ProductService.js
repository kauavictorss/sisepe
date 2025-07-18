export const ProductService = {
  async getProductsMini() {
    // Exemplo de dados mockados
    return [
      { id: 1, name: 'Produto 1', category: 'Categoria A', quantity: 10 },
      { id: 2, name: 'Produto 2', category: 'Categoria B', quantity: 5 },
      { id: 3, name: 'Produto 3', category: 'Categoria C', quantity: 8 },
    ];
  }
};
