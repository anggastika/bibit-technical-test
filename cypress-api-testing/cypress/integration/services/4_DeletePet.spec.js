describe('Delete Pet', () => {
  it('Success delete Pet', () => {
      cy.postPet().then((responsePostPet) => {
          cy.deletePet(responsePostPet.body.id).should((response) => {
              expect(response.status).to.eq(200)
          })
      })
  })
})