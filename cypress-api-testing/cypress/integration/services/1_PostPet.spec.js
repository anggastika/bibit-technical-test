import petSchema from '../../contracts/pet.contract'

describe('PostPet', () => {
  it('Success Post /pet', () => {
      cy.postPet().should((response) => {
          expect(response.status).to.eq(200)
          expect(response.body.id).to.be.not.null
          expect(response.body.category.id).to.be.not.null
          return petSchema.validateAsync(response.body)
      })
  })
});