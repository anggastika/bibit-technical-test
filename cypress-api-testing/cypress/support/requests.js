//POST
Cypress.Commands.add('postPet', () => {
    cy.request({
        method: "POST",
        failOnStatusCode: false,
        url: `/pet`,
        headers: {
            accept: "application/json"
        },
        body: {
            "id": 1,
            "category": {
              "id": 1,
              "name": "harimau"
            },
            "name": "doggie",
            "photoUrls": [
              "autodika.com"
            ],
            "tags": [
              {
                "id": 1,
                "name": "tags name"
              }
            ],
            "status": "available"
          }
    })
})

//GET
Cypress.Commands.add('getPetByStatus', () => {
    cy.request({
        method: 'GET',
        failOnStatusCode: false,
        url: `/pet/findByStatus?status=available`
    })
})

Cypress.Commands.add('getPetById', (id) => {
    cy.request('GET',`/pet/${id}`)
})

//PUT
Cypress.Commands.add('putPet', () => {
    cy.request({
        method: "PUT",
        failOnStatusCode: false,
        url: `/pet`,
        headers: {
            accept: "application/json"
        },
        body: {
            "id": 1,
            "category": {
              "id": 1,
              "name": "edit"
            },
            "name": "edit",
            "photoUrls": [
              "edit"
            ],
            "tags": [
              {
                "id": 1,
                "name": "edit"
              }
            ],
            "status": "available"
          }
    })
})

//DELETE
Cypress.Commands.add('deletePet', (id) => {
    cy.request({
        method: "DELETE",
        url: `/pet/${id}`,
        headers: {
            accept: "application/json"
        }
    })
})