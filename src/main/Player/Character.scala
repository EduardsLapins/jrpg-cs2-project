package Player

class Character (var maxhp: Int, var hp: Int, var attk: Int, var deff: Int, var magattk: Int, var magdeff: Int, var maxmana: Int, var mana: Int){

  var isAlive: Boolean = true

  def takeDamage(attkStrength: Int): Unit = {
    if (this.hp > 0) {
      this.hp -= attkStrength
      if (this.hp <= 0) {
        isAlive = false
      }
    }
    else {
      isAlive = false
    }
  }

  def physAttack(cietejs: Character): Unit ={
    var saape: Int = (this.attk * (this.attk.toFloat / cietejs.deff.toFloat)).round
    cietejs.takeDamage(saape)
  }

  def magAttack(cietejs: Character): Unit ={
    if(this.mana > 5) {
      var magSaape: Int = (this.magattk * (this.magattk.toFloat / cietejs.magdeff.toFloat)).round
      this.mana -= 5
      cietejs.takeDamage(magSaape)
    }
    else if(this.mana > 0) {
      var magSaape: Int = math.ceil(this.magattk.toFloat * (this.mana.toFloat/5.toFloat) * (this.magattk.toFloat / cietejs.magdeff.toFloat)).toInt
      this.mana = 0
      cietejs.takeDamage(magSaape)
    }
  }

}
