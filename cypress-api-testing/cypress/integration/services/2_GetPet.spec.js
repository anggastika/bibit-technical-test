import petSchema from '../../contracts/pet.contract'

describe('Get Pet By ID', () => {

    it('Get Pet By ID',() => {
        cy.postPet().then((postPet) => {
            cy.getPetById(postPet.body.id).should((response) => {
              expect(response.status).to.eq(200)
              return petSchema.validateAsync(response.body)
            })
        })
    })

    it('Get Pet By Status',() => {
      cy.getPetByStatus().then((response) => {
        expect(response.status).to.eq(200)
        expect(response.body.id).to.be.not.null
      })
  })
  })