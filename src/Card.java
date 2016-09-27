
public class Card
	{
		private String name;
		private int value;
		private String suit;
		
		public Card(String n, int r, String s)
		{
			name = n;
			value = r;
			suit = s;
		}

		public String getName()
			{
				return name;
			}

		public void setName(String name)
			{
				this.name = name;
			}

		public int getValue()
			{
				return value;
			}

		public void setValue(int rank)
			{
				this.value = rank;
			}

		public String getSuit()
			{
				return suit;
			}

		public void setSuit(String suit)
			{
				this.suit = suit;
			}

		@Override
		public String toString()
			{
				return  name + " of "  + suit;
			}		
	}
