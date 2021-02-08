package Player

abstract class Character (var isAlly: Boolean, var maxhp: Int, var hp: Int, var attk: Int, var deff: Int, var magattk: Int, var magdeff: Int, var maxmana: Int, var mana: Int){

  var isAlive: Boolean = true
  var exp: Int = 0
  var lvl: Int = 1

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

  def expForKO(deadboi: Character): Unit ={
    this.exp += (16 * (1f * deadboi.maxhp / this.maxhp)).toInt
  }

  def physAttack(cietejs: Character): Unit ={
    if (this.isAlive && cietejs.isAlive) {
      var saape: Int = (this.attk * (this.attk.toFloat / cietejs.deff.toFloat)).round
      if (saape < cietejs.hp) {
        this.exp += 2 + (10f * (saape.toFloat / cietejs.hp.toFloat)).toInt
      }
      else{expForKO(cietejs)}
      cietejs.takeDamage(saape)
      this.lvlup()
    }
  }

  def magAttack(cietejs: Character): Unit ={
    if (this.isAlive && cietejs.isAlive) {
      if (this.mana > 5) {
        var magSaape: Int = (this.magattk * (this.magattk.toFloat / cietejs.magdeff.toFloat)).round
        this.mana -= 5
        if (magSaape < cietejs.hp) {
          this.exp += 2 + (10f * (magSaape.toFloat / cietejs.hp.toFloat)).toInt
        }
        else{expForKO(cietejs)}
        cietejs.takeDamage(magSaape)
        this.lvlup()
      }
      else if (this.mana > 0) {
        var magSaape: Int = math.ceil(this.magattk.toFloat * (this.mana.toFloat / 5.toFloat) * (this.magattk.toFloat / cietejs.magdeff.toFloat)).toInt
        this.mana = 0
        if (magSaape < cietejs.hp) {
          this.exp += 2 + (10f * (magSaape.toFloat / cietejs.hp.toFloat)).toInt
        }
        else{expForKO(cietejs)}
        cietejs.takeDamage(magSaape)
        this.lvlup()
      }
    }
  }
  def lvlup(): Unit ={
    if(this.exp >= (100 * this.lvl)){
      this.lvl += (exp.toFloat/100f).floor.toInt

      this.maxhp += 5 * (exp.toFloat/100f).floor.toInt
      this.hp += (this.maxhp - this.hp)

      this.attk += 3 * (exp.toFloat/100f).floor.toInt
      this.deff += 3 * (exp.toFloat/100f).floor.toInt

      this.magattk += 3 * (exp.toFloat/100f).floor.toInt
      this.magdeff += 3 * (exp.toFloat/100f).floor.toInt

      this.maxmana += 5 * (exp.toFloat/100f).floor.toInt
      this.mana += (this.maxmana - this.mana)

      this.exp -= 100 * (exp.toFloat / 100f).floor.toInt
    }
  }

  def battleOptions(): List[String] ={
    var actions: List[String] = List("Locked", "Locked", "Locked", "Locked")
    actions
  }
  def takeAction(action: String, cits_varonis: Character): Unit = {

  }

}
