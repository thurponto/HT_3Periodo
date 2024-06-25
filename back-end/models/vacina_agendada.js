const Sequelize = require('sequelize');
module.exports = function(sequelize, DataTypes) {
  return sequelize.define('vacina_agendada', {
    id: {
      autoIncrement: true,
      type: DataTypes.BIGINT,
      allowNull: false,
      primaryKey: true
    },
    idoso_id: {
      type: DataTypes.BIGINT,
      allowNull: false,
      references: {
        model: 'idoso',
        key: 'id'
      }
    },
    vacina_id: {
      type: DataTypes.BIGINT,
      allowNull: false,
      references: {
        model: 'vacina',
        key: 'id'
      }
    },
    data_agendamento: {
      type: DataTypes.DATEONLY,
      allowNull: false
    }
  }, {
    sequelize,
    tableName: 'vacina_agendada',
    timestamps: false,
    indexes: [
      {
        name: "PRIMARY",
        unique: true,
        using: "BTREE",
        fields: [
          { name: "id" },
        ]
      },
      {
        name: "idoso_id",
        using: "BTREE",
        fields: [
          { name: "idoso_id" },
        ]
      },
      {
        name: "vacina_id",
        using: "BTREE",
        fields: [
          { name: "vacina_id" },
        ]
      },
    ]
  });
};
