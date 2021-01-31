import Player.Character
import Player.Party
import org.scalatest._

class partyExpTest extends FunSuite {

  test("testing party exp") {
    val BigMac: Character = new Character(45, 10, 20, 30, 11, 39, 30, 30)
    val Coke: Character = new Character(45, 20, 15, 50, 10, 15, 30, 4)

    val Pizza: Character = new Character(45, 10, 20, 30, 11, 39, 30, 30)
    val Fanta: Character = new Character(45, 20, 10, 50, 10, 15, 30, 4)

    val McD: List[Character] = List(BigMac, Coke)
    val AxisP: List[Character] = List(Pizza, Fanta)

    val MickyD_Squad: Party = new Party(McD)
    //val AxisPowers: Party = new Party(AxisP)


    BigMac.physAttack(Fanta)
    Fanta.physAttack(BigMac)
    BigMac.physAttack(Fanta)
    Fanta.physAttack(BigMac)
    assert(Fanta.exp == 11)
    //Big M wins
    BigMac.physAttack(Fanta)
    assert(BigMac.exp == 30)

    Coke.physAttack(Pizza)
    Pizza.physAttack(Coke)
    assert(Pizza.exp == 6)
    //Coke wins
    Coke.physAttack(Pizza)
    assert(Coke.exp == 26)

    //AxisPowers - defeated
    MickyD_Squad.partyExp(AxisP)

    //Checking if each McD squad member gets the appropriate 17/2 = 8 exp (round down)
    assert(BigMac.lvl == 1)
    assert(BigMac.exp == 38)
    assert(Coke.lvl == 1)
    assert(Coke.exp == 34)


  }

  test("testing party but with more bloodshed") {
    val BigMac: Character = new Character(45, 10, 20, 30, 11, 39, 30, 30)
    val FrenchFries: Character = new Character(45, 20, 15, 50, 10, 15, 30, 4)
    val Coke: Character = new Character(45, 20, 15, 50, 10, 15, 30, 4)

    val Pizza: Character = new Character(45, 10, 20, 30, 11, 39, 30, 30)
    val Fanta: Character = new Character(45, 20, 10, 50, 10, 15, 30, 4)

    val McD: List[Character] = List(BigMac, Coke)
    val AxisP: List[Character] = List(Pizza, Fanta)

    val MickyD_Squad: Party = new Party(McD)
    //val AxisPowers: Party = new Party(AxisP)

    Fanta.physAttack(FrenchFries)
    Fanta.physAttack(FrenchFries)
    Fanta.physAttack(FrenchFries)
    Fanta.physAttack(FrenchFries)
    Fanta.physAttack(FrenchFries)
    Fanta.physAttack(FrenchFries)
    Fanta.physAttack(FrenchFries)
    //Fanta really doesn't like French, fries.
    assert(Fanta.exp == 23)

    BigMac.physAttack(Fanta)
    Fanta.physAttack(BigMac)
    BigMac.physAttack(Fanta)
    Fanta.physAttack(BigMac)
    assert(Fanta.exp == 34)
    //Big M wins
    BigMac.physAttack(Fanta)
    assert(BigMac.exp == 30)

    Coke.physAttack(Pizza)
    Pizza.physAttack(Coke)
    assert(Pizza.exp == 6)
    //Coke wins
    Coke.physAttack(Pizza)
    assert(Coke.exp == 26)

    //AxisPowers - defeated
    MickyD_Squad.partyExp(AxisP)

    //Checking if each alive McD squad member gets the appropriate 40/2 = 20 exp
    assert(BigMac.lvl == 1)
    assert(BigMac.exp == 50)
    assert(Coke.lvl == 1)
    assert(Coke.exp == 46)

    //Frenchies get nothing
    assert(FrenchFries.exp == 0)
  }
}
