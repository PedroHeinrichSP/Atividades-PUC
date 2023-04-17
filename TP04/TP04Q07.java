class TP04Q07 {
    class Celula {
        public int elemento;
        public Celula inf, sup, esq, dir;

        public Celula() {
            this(0);
        }

        public Celula(int elemento) {
            this(elemento, null, null, null, null);
        }

        public Celula(int elemento, Celula inf, Celula sup, Celula esq, Celula dir) {
            this.elemento = elemento;
            this.inf = inf;
            this.sup = sup;
            this.esq = esq;
            this.dir = dir;
        }
    }

    class Matriz {
        private Celula inicio;
        private int linha, coluna;

        public Matriz() {
            this(3,3);
        }

        public Matriz(int linha, int coluna) {
            this.linha = linha;
            this.coluna = coluna;
            this.inicio = new Celula();
            //Cria uma "pilha de pilhas"
            Celula auxV = inicio;
            Celula auxH = inicio;
            for(int i=0; i<linha; i++){
                for(int j=0; j<coluna; j++){
                    auxH.dir = new Celula();
                    auxH.dir.esq = auxH;
                    auxH = auxH.dir;
                }
                auxV.inf = new Celula();
                auxV = auxV.inf;
                auxH = auxV;
            }
            //Interconecta verticalmente
            auxH = auxV = inicio;
            Celula auxV2 = inicio.inf;
            Celula auxH2 = inicio.inf;
            for(int i=0; i<linha-1; i++){
                for(int j=0; j<coluna; j++){
                    auxH.inf = auxH2;
                    auxH2.sup = auxH;
                    auxH = auxH.dir;
                    auxH2 = auxH2.dir;
                }
                auxV = auxV.inf;
                auxV2 = auxV2.inf;
                auxH = auxV;
                auxH2 = auxH2.inf;
            }   
        }

        public Matriz soma (Matriz m) {
            Matriz resp = null;
            if(this.linha == m.linha && this.coluna == m.coluna){
                resp = new Matriz(this.linha, this.coluna);
                Celula auxV = resp.inicio;
                Celula auxH = resp.inicio;
                Celula auxV_this = this.inicio;
                Celula auxH_this = this.inicio;
                Celula auxV_m = m.inicio;
                Celula auxH_m = m.inicio;
                for(int i=0;i<linha;i++){
                    for(int j=0; j<coluna; j++){
                        auxH.elemento = auxH_this.elemento + auxH_m.elemento;
                        auxH = auxH.dir;
                        auxH_m = auxH_m.dir;
                        auxH_this = auxH_this.dir;
                    }
                    auxH = auxV = auxV.inf;
                    auxH_this = auxV_this = auxV_this.inf;
                    auxH_m = auxV_m = auxV_m.inf;
                }
            }
            return resp;
        }

        public Matriz multiplicacao (Matriz m) {
            /*Matriz a*b (sendo 2,2)
             *  (a00)(a01)
             *  (a10)(a11)          
             *                      c00 = a00*b00 + a01*b10
             *  (b00)(b01)          c01 = a00*b01 + a01*b11
             *  (b10)(b11)          c10 = a10*b00 + a11*b10
             *                      c11 = a10*b01 + a11*b11
             *  (c00)(c01)
             *  (c10)(c11)
             * 
             *Matriz a*b (sendo a3,2; b2,3)
             *  (a00)(a01)(a02)
             *  (a10)(a11)(a12)
             *                      c00 = a00*b00 + a01*b10 + a02*b20
             *  (b00)(b01)          c01 = a00*b01 + a01*b11 + a02*b21
             *  (b10)(b11)          c10 = a10*b00 + a11*b01 + a12*b20
             *  (b20)(b21)          c11 = a10*b01 + a11*b11 + a12*b21
             * 
             *  (c00)(c01)
             *  (c10)(c11)
             */
            
            Matriz resp = null;
            if(this.coluna == m.linha){
                resp = new Matriz(this.linha, m.coluna);
                Celula auxV = resp.inicio;
                Celula auxH = resp.inicio;
                Celula auxV_this = this.inicio;
                Celula auxH_this = this.inicio;
                Celula auxV_m = m.inicio;
                Celula auxH_m = m.inicio;
                // I e J são respectivamente linha e coluna de C (Cij)
                // N é o numero de colunas de A ou linhas de B
                // C(i,j) = A(i,n) * B(n,j), num loop
                for(int i=0;i<resp.linha;i++){
                    for(int j=0; j<resp.coluna; j++){
                        multiplicacao(i, j, m);
                    }
                    auxH = auxV = auxV.inf;
                    auxH_this = auxV_this = auxV_this.inf;
                    auxH_m = auxV_m = auxV_m.inf;
                }
            }
            return resp;
        }

        private int multiplicacao(int lin, int col, Matriz b){
            int resp = 0;
            Celula auxHV_this = this.inicio, auxHV_b = b.inicio;
            
            for(int i=0; i<this.coluna; i++){

            }
            return resp;
        }

        public boolean isQuadrada(){
           return (this.linha == this.coluna);
        }

        public void mostrarDiagonalPrincipal() {
            if (isQuadrada()) {
                Celula auxHV = this.inicio;
                for(int i=0; i<this.linha; i++){
                    System.out.println(auxHV.elemento);
                    auxHV=auxHV.inf;
                    auxHV=auxHV.dir;
                }

            }
        }

        public void mostrarDiagonalSecundaria() {
            if (isQuadrada()) {
                Celula auxHV;
                for(auxHV = this.inicio; auxHV.inf != null; auxHV = auxHV.inf);
                for(int i=0; i<this.linha; i++){
                    System.out.println(auxHV.elemento);
                    auxHV=auxHV.sup;
                    auxHV=auxHV.dir;
                }
            }
        }
    }

    static public void main(String[] args) {

    }
}