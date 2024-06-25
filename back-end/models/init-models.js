var DataTypes = require("sequelize").DataTypes;
var _agenda = require("./agenda");
var _agente_saude = require("./agente_saude");
var _historico = require("./historico");
var _idoso = require("./idoso");
var _sequelizemeta = require("./sequelizemeta");
var _users = require("./users");
var _vacina = require("./vacina");
var _vacina_agendada = require("./vacina_agendada");

function initModels(sequelize) {
  var agenda = _agenda(sequelize, DataTypes);
  var agente_saude = _agente_saude(sequelize, DataTypes);
  var historico = _historico(sequelize, DataTypes);
  var idoso = _idoso(sequelize, DataTypes);
  var sequelizemeta = _sequelizemeta(sequelize, DataTypes);
  var users = _users(sequelize, DataTypes);
  var vacina = _vacina(sequelize, DataTypes);
  var vacina_agendada = _vacina_agendada(sequelize, DataTypes);

  historico.belongsTo(agenda, { as: "agenda", foreignKey: "agenda_id"});
  agenda.hasMany(historico, { as: "historicos", foreignKey: "agenda_id"});
  agenda.belongsTo(agente_saude, { as: "agente_saude", foreignKey: "agente_saude_id"});
  agente_saude.hasMany(agenda, { as: "agendas", foreignKey: "agente_saude_id"});
  agenda.belongsTo(idoso, { as: "idoso", foreignKey: "idoso_id"});
  idoso.hasMany(agenda, { as: "agendas", foreignKey: "idoso_id"});
  historico.belongsTo(idoso, { as: "idoso", foreignKey: "idoso_id"});
  idoso.hasMany(historico, { as: "historicos", foreignKey: "idoso_id"});
  vacina_agendada.belongsTo(idoso, { as: "idoso", foreignKey: "idoso_id"});
  idoso.hasMany(vacina_agendada, { as: "vacina_agendadas", foreignKey: "idoso_id"});
  historico.belongsTo(vacina, { as: "vacina", foreignKey: "vacina_id"});
  vacina.hasMany(historico, { as: "historicos", foreignKey: "vacina_id"});
  vacina_agendada.belongsTo(vacina, { as: "vacina", foreignKey: "vacina_id"});
  vacina.hasMany(vacina_agendada, { as: "vacina_agendadas", foreignKey: "vacina_id"});

  return {
    agenda,
    agente_saude,
    historico,
    idoso,
    sequelizemeta,
    users,
    vacina,
    vacina_agendada,
  };
}
module.exports = initModels;
module.exports.initModels = initModels;
module.exports.default = initModels;
