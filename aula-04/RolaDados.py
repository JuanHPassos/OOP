from Dado import Dado 
import random

class RolaDados:
  def __init__(self, n, seed):
    self.dados = []
    self.r = random.Random(seed)
    for i in range(n):
      if seed == 0:
        self.dados.append(Dado())
      else:
        self.dados.append(Dado(6,self.r.randint(1,10000)))
        
  def rolar(self, input=None):
    if input is None:
      # Rola todos os dados se nenhum parâmetro for fornecido
      return [d.rolar() for d in self.dados]
          
    # Cria lista de booleanos inicializada como False
    rolaDado = [False] * len(self.dados)
    
    # Divide a string em partes e processa cada número
    for face in input.split():
        try:
            i = int(face) - 1  # Converte para índice 0-based
            # Dado a ser rolado
            if 0 <= i < len(self.dados):
                rolaDado[i] = True
        except ValueError:
            continue  # Ignora valores que não são uma face do dado
    # Armazena novos valores dos dados
    r = []
    
    # Determinar novos valores dos dados
    for i in range(len(self.dados)):
      if ( rolaDado[i] ):
        self.dados[i].rolar()
      r.append(self.dados[i].getLado())
    
    return r
  
  # Imprimir valores e os dados
  def __str__(self):
    valorDados = ""
    for i in range (5):
      base = i*8
      for dado in self.dados:
        valorDado = dado.__str__()
        valorDados += valorDado[base:base + 7]
        valorDados += "    "
        
      valorDados += "\n"
        
    return valorDados
      
    

       