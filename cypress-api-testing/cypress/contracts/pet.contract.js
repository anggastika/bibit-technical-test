import Joi from '@hapi/joi';

const petSchema = Joi.object({
    id: Joi.number(),
    category: Joi.object({
      id: Joi.number(),
      name: Joi.string()
    }), 
    name: Joi.string(),
    photoUrls: Joi.array(),
    tags: Joi.array(),
    status: Joi.string()
})

export default petSchema;