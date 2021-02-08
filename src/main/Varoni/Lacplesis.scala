package Varoni
import Player.Character

class Lacplesis extends Character(true, 100, 100, 30, 25, 0, 10, 0, 0){

  override def lvlup(): Unit = {
    if(this.exp >= (100 * this.lvl)){
      this.lvl += (exp.toFloat/100f).floor.toInt

      this.maxhp += 5 * (exp.toFloat/100f).floor.toInt
      this.hp += (this.maxhp - this.hp)

      this.attk += 3 * (exp.toFloat/100f).floor.toInt
      this.deff += 3 * (exp.toFloat/100f).floor.toInt

      this.magattk += 0 * (exp.toFloat/100f).floor.toInt
      this.magdeff += 1 * (exp.toFloat/100f).floor.toInt

      this.maxmana +=  1 * (exp.toFloat/100f).floor.toInt
      this.mana += (this.maxmana - this.mana)

      this.exp -= 100 * (exp.toFloat / 100f).floor.toInt
    }
  }


  //physAttack, but ignores defense and is 3 times weaker
  def laca_dure(cietejs: Character): Unit ={
    if (this.isAlive && cietejs.isAlive) {
      var saape: Int = (this.attk * 0.33f).round
      if (saape < cietejs.hp) {
        this.exp += 2 + (10f * (saape.toFloat / cietejs.hp.toFloat)).toInt
      }
      else{expForKO(cietejs)}
      cietejs.takeDamage(saape)
      this.lvlup()
    }
  }

  // healing
  def heal(): Unit ={
    if(this.hp < this.maxhp - 20){
      this.hp += 20
    }
    else if(this.hp < this.maxhp){
      this.hp += (this.maxhp - this.hp)
    }
  }

  // high damage attack, depletes mana
  def laca_speks(cietejs: Character): Unit ={
    if (this.isAlive && cietejs.isAlive && this.mana >= 15) {
      var saape: Int = 2 * (this.attk * (this.attk.toFloat / (0.75f * cietejs.deff.toFloat))).round
      this.mana -= 15
      if (saape < cietejs.hp) {
        this.exp += 2 + (10f * (saape.toFloat / cietejs.hp.toFloat)).toInt
      }
      else{expForKO(cietejs)}
      cietejs.takeDamage(saape)
      this.lvlup()
    }
    else if (this.isAlive && cietejs.isAlive && this.mana > 0) {
      var magSaape: Int = 2 * math.ceil(this.attk.toFloat * (this.mana.toFloat / 15.toFloat) * (this.attk.toFloat / (0.75f * cietejs.deff.toFloat))).toInt
      this.mana = 0
      if (magSaape < cietejs.hp) {
        this.exp += 2 + (10f * (magSaape.toFloat / cietejs.hp.toFloat)).toInt
      }
      else{expForKO(cietejs)}
      cietejs.takeDamage(magSaape)
      this.lvlup()
    }
  }

  override def battleOptions(): List[String] = {
    var actions: List[String] = List("Zobena Cirtiens", "Gulēt", "Lāča Dūre", "Lāča Spēks")
    actions
  }

  override def takeAction(action: String, cits_varonis: Character): Unit = {
    val actions = battleOptions()
    if (action == actions.head && !cits_varonis.isAlly) {
      this.physAttack(cits_varonis)
    }
    else if (action == actions(1)){
      this.heal()
    }
    else if(action == actions(2) && this.lvl >= 5 && !cits_varonis.isAlly){
      this.laca_dure(cits_varonis)
    }
    else if(action == actions(3) && this.lvl >= 15 && !cits_varonis.isAlly){
      this.laca_speks(cits_varonis)
    }
  }
}
