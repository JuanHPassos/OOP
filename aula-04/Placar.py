class Placar:
    def __init__(self):
        self.POSICOES = 10
        self.placar = [0] * 10
        self.taken = [False] * 10

    def add(self, posicao, dados):
        if self.taken[posicao - 1]:
            raise ValueError("Posição ocupada")
        
        k = 0
        if (posicao == 1):
            k = self.conta(1, dados)
        elif (posicao == 2):
            k = 2 * self.conta(2, dados)
        elif (posicao == 3):
            k = 3 * self.conta(3, dados)
        elif (posicao == 4):
            k = 4 * self.conta(4, dados)
        elif (posicao == 5):
            k = 5 * self.conta(5, dados)
        elif (posicao == 6):
            k = 6 * self.conta(6, dados)
        elif (posicao == 7):
            if self.checkFull(dados):
                k = 15
        elif (posicao == 8):
            if self.checkSeqMaior(dados):
                k = 20
        elif (posicao == 9):
            if self.checkQuadra(dados):
                k = 30
        elif (posicao == 10):
            if self.checkQuina(dados):
                k = 40
        else:
            raise ValueError("Valor da posição ilegal")
        
        self.placar[posicao-1] = k
        self.taken[posicao-1] = True

    def getScore(self):
        t = 0

        for i in range(self.POSICOES):
            if self.taken[i]:
                t += self.placar[i]

        return t

    def conta(self, n, vet):
        cont = 0

        for i in vet:
            if i == n:
                cont += 1

        return cont
    
    def checkFull(self, dados):
        v = dados
        v.sort()
        return (v[0] == v[1] and v[1] == v[2] and v[3] == v[4]) or \
               (v[0] == v[1] and v[2] == v[3] and v[3] == v[4])
    
    def checkQuadra(self, dados):
        v = dados
        v.sort()
        return (v[0] == v[1] and v[1] == v[2] and v[2] == v[3]) or \
               (v[1] == v[2] and v[2] == v[3] and v[3] == v[4])
    
    def checkQuina(self, dados):
        v = dados
        return (v[0] == v[1] and v[1] == v[2] and v[2] == v[3] and v[3] == v[4])
    
    def checkSeqMaior(self, dados):
        v = dados
        dados.sort()
        return (v[0] == v[1]-1 and v[1] == v[2]-1 and \
                v[2] == v[3]-1 and v[3] == v[4]-1)
    
    def __str__(self):
        s = ""
        for i in range(3):
            # Primeira coluna
            num = f" {self.placar[i]:<3}" if self.taken[i] else f"({i+1}) "
            s += num + "   |   "
            
            # Segunda coluna
            num = f" {self.placar[i+6]:<3}" if self.taken[i+6] else f"({i+7}) "
            s += num + "   |  "
            
            # Terceira coluna
            num = f" {self.placar[i+3]:<3}" if self.taken[i+3] else f"({i+4}) "
            s += num + "\n-------|----------|-------\n"

        # Última linha (centro)
        num = f" {self.placar[9]:<3}" if self.taken[9] else "(10)"
        s += "       |   " + num + "   |"
        s += "\n       +----------+\n"
        return s