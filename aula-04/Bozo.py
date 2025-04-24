from Placar import Placar
from RolaDados import RolaDados

NRODADAS = 10

def main():
    print("Digite a semente (zero para aleatório): ", end="")
    seed = int(input())
    rd = RolaDados(5, seed)
    pl = Placar()
    print(pl)
    for rodada in range(NRODADAS):
        print("****** Rodada " + str((rodada+1)))
        print("Pressione ENTER para lançar os dados")
        input()

        rd.rolar()
        print("1          2          3          4          5")
        print(rd)

        print("Digite os números dos dados que quiser TROCAR. Separados por espaços.")
        muda = input()
        rd.rolar(muda)
        print("1          2          3          4          5")
        print(rd)

        print("Digite os números dos dados que quiser TROCAR. Separados por espaços.")
        muda = input()
        values = rd.rolar(muda)
        print("1          2          3          4          5")
        print(rd)

        print("\n\n")
        print(pl)
        pos = 0

        while pos <= 0:  # Infinite loop until we get valid input
            try:
                print("Escolha a posição que quer ocupar com essa jogada ===> ", end='')
                pos = int(input())
                
                if pos < 1 or pos > NRODADAS:
                    pos = 0
                    
                pl.add(pos, values)
            except ValueError as e:
                pos = 0
            if pos == 0:
                print("Valor inválido. Posição ocupada ou inexistente.")

        print("\n\n")
        print(pl)

    print("***********************************")
    print("***")
    print("*** Seu escore final foi: " + str(pl.getScore()))
    print("***")
    print("***********************************")

if __name__ == "__main__":
    main()