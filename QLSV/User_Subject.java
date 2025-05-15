package QLSV;


public class User_Subject {
		private int userSubjectID;
		private int userID;
		private int subjectID;
		private float diemHe1;
		private float diemHe2;
		private float diemHe3;
		private float diemHe4;
		private float diemHe5;
		
		public User_Subject() {}
		
		public User_Subject(int userID, int subjectID, float diemHe1, float diemHe2, float diemHe3, float diemHe4, float diemHe5) {
			this.userID = userID;
			this.subjectID = subjectID;
			this.diemHe1 = diemHe1;
			this.diemHe2 = diemHe2;
			this.diemHe3 = diemHe3;
			this.diemHe4 = diemHe4;
			this.diemHe5 = diemHe5;
		}
		
		public User_Subject(int UserSubjectID, int userID, int subjectID, float diemHe1, float diemHe2, float diemHe3, float diemHe4, float diemHe5) {
			this.userSubjectID = UserSubjectID;
			this.userID = userID;
			this.subjectID = subjectID;
			this.diemHe1 = diemHe1;
			this.diemHe2 = diemHe2;
			this.diemHe3 = diemHe3;
			this.diemHe4 = diemHe4;
			this.diemHe5 = diemHe5;
		}

		public int getUserSubjectID() {
			return userSubjectID;
		}

		public void setUserSubjectID(int UserSubjectID) {
			this.userSubjectID = UserSubjectID;
		}

		public int getuserID() {
			return userID;
		}

		public void setuserID(int userID) {
			this.userID = userID;
		}

		public int getsubjectID() {
			return subjectID;
		}

		public void setsubjectID(int subjectID) {
			this.subjectID = subjectID;
		}

		public float getDiemHe1() {
			return diemHe1;
		}

		public void setDiemHe1(float diemHe1) {
			this.diemHe1 = diemHe1;
		}

		public float getDiemHe2() {
			return diemHe2;
		}

		public void setDiemHe2(float diemHe2) {
			this.diemHe2 = diemHe2;
		}

		public float getDiemHe3() {
			return diemHe3;
		}

		public void setDiemHe3(float diemHe3) {
			this.diemHe3 = diemHe3;
		}

		public float getDiemHe4() {
			return diemHe4;
		}

		public void setDiemHe4(float diemHe4) {
			this.diemHe4 = diemHe4;
		}

		public float getDiemHe5() {
			return diemHe5;
		}

		public void setDiem5(float diemHe5) {
			this.diemHe5 = diemHe5;
		}
	
}
