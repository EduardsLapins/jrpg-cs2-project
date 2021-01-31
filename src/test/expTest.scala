import Player.Character
import org.scalatest._

class expTest extends FunSuite{
  test("testing exp and physAttack") {
    val Sitejs: Character = new Character(45,10, 20, 30, 10, 39, 30, 30)
    val Mizejs: Character = new Character(100,20, 10, 50, 10, 15, 30, 4)

    //kaujas
    //(translation: Simulation of a physical fight)
    assert(Mizejs.isAlive)
    Sitejs.physAttack(Mizejs)

    /*+4 exp Sitejs*/
    assert(Sitejs.exp == 6)

    assert(Mizejs.hp == 12)
    assert(Mizejs.isAlive)
    Sitejs.physAttack(Mizejs)
    assert(Mizejs.hp == 4)

    /*+8 exp Sitejs*/
    assert(Sitejs.exp == 14)

    //beidzot paruba
    //(translation: Fight ends in defender dying)
    Sitejs.physAttack(Mizejs)
    assert(Mizejs.hp == -4)
    assert(Mizejs.isAlive == false)

    /*+35 exp Sitejs, cuz Mizejs, technically was a lot stronger*/
    assert(Sitejs.exp == 49)
    /*not leveled up*/
    assert(Sitejs.lvl == 1)

  }

  test("testing exp and magAttack") {
    val Sitejs: Character = new Character(45,10, 20, 30, 6, 39, 32, 31)
    val Mizejs: Character = new Character(20,20, 10, 50, 10, 19, 30, 4)

    //baigi kaujas
    //(translation: Attacker uses magical attack, wastes his mana)
    assert(Mizejs.isAlive)
    Sitejs.magAttack(Mizejs)
    assert(Mizejs.hp == 18)
    assert(Mizejs.isAlive)
    Sitejs.magAttack(Mizejs)
    assert(Mizejs.hp == 16)
    Sitejs.magAttack(Mizejs)
    assert(Mizejs.hp == 14)
    assert(Mizejs.isAlive)
    assert(Sitejs.mana == 16)

    /*+9 exp in total to Sitejs*/
    assert(Sitejs.exp == 9)

    //turpina šaut pupiņas
    //(translation: Attacker keeps fighting, almost drained all mana)
    Sitejs.magAttack(Mizejs)
    Sitejs.magAttack(Mizejs)
    Sitejs.magAttack(Mizejs)
    assert(Mizejs.hp == 8)
    assert(Mizejs.isAlive)
    assert(Sitejs.mana == 1)

    /*+10 exp to Sitejs after draining mana*/
    assert(Sitejs.exp == 19)

    //šauj vēl mīkstākas pupiņas
    //(translation: Character drains last bit of mana, damage decreased)
    Sitejs.magAttack(Mizejs)
    assert(Sitejs.mana == 0)
    assert(Mizejs.isAlive)
    assert(Mizejs.hp == 7)

    /*+3 exp to Sitejs cuz attacks do next to nothing*/
    assert(Sitejs.exp == 22)

    //vēlreiz pakutina, bet nesanāk
    //(translation: Mana has been depleted, attacks do nothing)
    Sitejs.magAttack(Mizejs)
    assert(Sitejs.mana == 0)
    assert(Mizejs.isAlive)
    assert(Mizejs.hp == 7)

    /*no exp increase, as cannot attack, also no lvlup*/
    assert(Sitejs.exp == 22)
    assert(Sitejs.lvl == 1)
  }

  test("testing lvlup and stat incrementation") {
    val Sitejs: Character = new Character(45,10, 20, 30, 11, 39, 30, 30)
    val Mizejs: Character = new Character(450,8, 10, 50, 10, 15, 30, 4)

    Sitejs.physAttack(Mizejs)
    //levels up a single level, -100xp points necessary for the level-up
    assert(Sitejs.lvl == 2)
    assert(Sitejs.exp == 60)

    //all stats incremented by the appropriate amount
    assert(Sitejs.maxhp == 50)
    assert(Sitejs.hp == 50)
    assert(Sitejs.attk == 23)
    assert(Sitejs.deff == 33)
    assert(Sitejs.magattk == 14)
    assert(Sitejs.magdeff == 42)
    assert(Sitejs.maxmana == 35)
    assert(Sitejs.mana == 35)

  }

  test("testing multiple lvlup and stat incrementation") {
    val Sitejs: Character = new Character(45,10, 20, 30, 11, 39, 30, 30)
    val Mizejs: Character = new Character(900,8, 10, 50, 10, 15, 30, 4)

    Sitejs.physAttack(Mizejs)
    //levels up three times, -300xp points necessary for the level-ups
    /* When leveling up multiple levels in a single attack/kill the exp cap is intentionally not increased */
    assert(Sitejs.lvl == 4)
    assert(Sitejs.exp == 20)

    //all stats incremented by the appropriate amount
    assert(Sitejs.maxhp == 60)
    assert(Sitejs.hp == 60)
    assert(Sitejs.attk == 29)
    assert(Sitejs.deff == 39)
    assert(Sitejs.magattk == 20)
    assert(Sitejs.magdeff == 48)
    assert(Sitejs.maxmana == 45)
    assert(Sitejs.mana == 45)

  }

}
