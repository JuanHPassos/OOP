import random

class Dado:
  # Strings para formatar representação do dado
  s010 = "|  *  |\n"
  s100 = "|*    |\n"
  s001 = "|    *|\n"
  s000 = "|     |\n"
  s101 = "|*   *|\n"
  s111 = "|* * *|\n"
  
  # Dicionario das faces(Estrutura semelhante a struct)
  facesDoDado  = {
    1: s000 + s010 + s000,
    2: s100 + s000 + s001,
    3: s100 + s010 + s001,
    4: s101 + s000 + s101,
    5: s101 + s010 + s101,
    6: s111 + s000 + s111
  }
    
  # Construtor da classe dado, inicializa campos caso não sejam passados
  def __init__(self, lados=6, seed=None):
    self.lados = lados
    self.r = random.Random(seed)
    self.atual = self.rolar()

  # Rola o dado(gera valor entre 1 e self.lados)
  def rolar(self):
    # Gera número aleatorio entre 1 e a quant. das faces dos dados
    self.atual = self.r.randint(1, self.lados)
    return self.atual
  
  # Obtém resultado do lançar dos dados
  def getLado(self):
    return self.atual
  
  # Cria representação da face do dado
  def __str__(self):
    if (self.lados != 6): return "Não há representação para esse dados"
    s = "+-----+\n"
    # Concatena a representação da face atual na saída
    s += self.facesDoDado.get(self.getLado(), "")
    s += ("+-----+\n")
    return s
  
  
  
      