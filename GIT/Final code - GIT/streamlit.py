import streamlit as st
import pandas as pd



st.title("VEHICLE ENTRY AND EXIT DETAILS") #web page heading
#getting input from user and appending it to url


df = pd.read_csv("Book1.csv")
st.dataframe(data=df, width=610, height=400)
#st.write(df)

st.title("SEARCH VEHICLE")
user_input = st.text_input("Enter Plate Number")
df = df[df['Plate_Number'] == user_input.upper()]
st.dataframe(data=df, width=610, height=400)