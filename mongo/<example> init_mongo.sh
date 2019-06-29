#!/usr/bin/env bash 

mongo "mongodb+srv://cluster0-hol3x.mongodb.net/bookie_db" --username <replace with your username> --password <replace with your password>  "./bookie_initializer.js"