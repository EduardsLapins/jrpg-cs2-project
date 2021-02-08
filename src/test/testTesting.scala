import org.scalatest._
import Player.Character

class testTesting extends FunSuite{
  test("a simple test of all takeDamage functions") {
    class Boi extends Character(true, 45,10, 20, 30, 90, 39, 30, 4){}
    val Boi: Character = new Boi()
    //laimīgs dzīvo
    //(translation: Character is alive)
    assert(Boi.isAlive)

    //uzkrīt lāsteka ->  >;(
    //(translation: Character takes damage, but lives)
    Boi.takeDamage(5)
    assert(Boi.isAlive)
    assert(Boi.hp == 5)

    //uzkrīt flīģelis -> x_x
    //(translation: Character takes damage and dies)
    Boi.takeDamage(5)
    assert(Boi.hp == 0)
    assert(!Boi.isAlive)
  }

  test("testing physAttack") {
    class Sitejs extends Character(true,45,10, 20, 30, 10, 39, 30, 30){}
    class Mizejs extends Character(true, 20,20, 10, 50, 10, 15, 30, 4){}

    val Sitejs: Character = new Sitejs()
    val Mizejs: Character = new Mizejs()

    //kaujas
    //(translation: Simulation of a physical fight)
    assert(Mizejs.isAlive)
    Sitejs.physAttack(Mizejs)
    assert(Mizejs.hp == 12)
    assert(Mizejs.isAlive)
    Sitejs.physAttack(Mizejs)
    assert(Mizejs.hp == 4)

    //beidzot paruba
    //(translation: Fight ends in defender dying)
    Sitejs.physAttack(Mizejs)
    assert(Mizejs.hp == -4)
    assert(!Mizejs.isAlive)
  }

  test("testing magAttack") {
    class Sitejs extends Character(true,45,10, 20, 30, 6, 39, 32, 31){}
    class Mizejs extends Character(true, 20,20, 10, 50, 10, 19, 30, 4){}

    val Sitejs: Character = new Sitejs()
    val Mizejs: Character = new Mizejs()

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

    //turpina šaut pupiņas
    //(translation: Attacker keeps fighting, almost drained all mana)
    Sitejs.magAttack(Mizejs)
    Sitejs.magAttack(Mizejs)
    Sitejs.magAttack(Mizejs)
    assert(Mizejs.hp == 8)
    assert(Mizejs.isAlive)
    assert(Sitejs.mana == 1)

    //šauj vēl mīkstākas pupiņas
    //(translation: Character drains last bit of mana, damage decreased)
    Sitejs.magAttack(Mizejs)
    assert(Sitejs.mana == 0)
    assert(Mizejs.isAlive)
    assert(Mizejs.hp == 7)

    //vēlreiz pakutina, bet nesanāk
    //(translation: Mana has been depleted, attacks do nothing)
    Sitejs.magAttack(Mizejs)
    assert(Sitejs.mana == 0)
    assert(Mizejs.isAlive)
    assert(Mizejs.hp == 7)
  }
}
