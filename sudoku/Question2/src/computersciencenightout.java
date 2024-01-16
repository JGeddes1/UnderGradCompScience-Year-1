public class computersciencenightout {


    public static void main(String[] args) {
        computersciencenightout.Pubs Pub = new computersciencenightout.Pubs();
        computersciencenightout.Nightclub Club = new computersciencenightout.Nightclub();
        computersciencenightout.takeaway TakeAway = new computersciencenightout.takeaway();
        Pub.writeTo();
        Club.writeTo();
        TakeAway.writeTo();
    }

    static class Nightclub extends computersciencenightout.nightspot {
        public int DanceFloor;
        public double DJquality;
        public double wkdPrice;

        public Nightclub() {
            double WkdPrice = 7.15D;
            double intDJ = 0.2D;
            int floorcnt = 1;
            super.Name = "The Danger zone";
            super.Street = "cool street";
            super.Coolness = 9;
            this.DanceFloor = floorcnt;
            this.DJquality = intDJ;
            this.wkdPrice = WkdPrice;
        }

        public void writeTo() {
            System.out.println("The Club " + this.Name + " has a coolness score of " + this.Coolness + ", they are located at " + this.Street + ".This Club has WKD at £" + this.wkdPrice);
        }
    }

    static class Pubs extends computersciencenightout.nightspot {
        public double Grumpiness;
        public int bookCount;

        public Pubs() {
            double grumpiness = 0.79D;
            int bookcnt = 20;
            super.Name = "rovers return";
            super.Street = "corination street";
            super.Coolness = 4;
            this.bookCount = bookcnt;
            this.Grumpiness = grumpiness;
        }

        public void writeTo() {
            System.out.println("The Pub " + this.Name + " has a coolness of " + this.Coolness + ", they are at " + this.Street + ". The grumpiness is " + this.Grumpiness);
        }
    }

    static class nightspot {
        public String Name;
        public String Street;
        public int Coolness;

        nightspot() {
        }
    }

    static class takeaway extends computersciencenightout.nightspot {
        public double foodPois;
        public int fatCont;

        public takeaway() {
            super.Name = "Carl's Kebab";
            super.Street = "lord Mayors";
            super.Coolness = 2;
            this.foodPois = 0.89D;
            this.fatCont = 59;
        }

        public void writeTo() {
            System.out.println("The Takeaway " + this.Name + " has a coolness score of " + this.Coolness + ", they are located at " + this.Street + ".This takeaway has a food poisioning chance of" + this.foodPois);
        }
    }
}
