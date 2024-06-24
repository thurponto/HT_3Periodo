import UserModel from "../src/model/UserModel";

const models = {
  UserModel,
};

Object.values(models).forEach((model: any) => {
  if (model.initialize) {
    model.initialize();
  }
});

export default models;
