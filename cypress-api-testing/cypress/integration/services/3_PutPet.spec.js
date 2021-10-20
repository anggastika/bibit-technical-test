import petSchema from '../../contracts/pet.contract'

describe('PutPet', () => {
  it('Success Put /pet', () => {
      cy.putPet().should((response) => {
          expect(response.status).to.eq(200)
          expect(response.body.id).to.be.not.null
          expect(response.body.category.id).to.be.not.null
          return petSchema.validateAsync(response.body)
      })
  })
});