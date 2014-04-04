﻿using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Fhnw.Ecnf.RoutePlanner.RoutePlannerLib
{
    public class City
    {
        public string Name { get; set; }
        public string Country { get; set; }
        public int Population { get; set; }
        public WayPoint Location { get; set; }
    
        public City(string name, string country, int population, double latitude, double longitude)
        {
            this.Name = name;
            this.Country = country;
            this.Population = population;
            this.Location = new WayPoint(name, latitude, longitude);
        }

        public override bool Equals(object obj)
        {

            // If parameter is null return false.
            if (obj == null)
            {
                return false;
            }
            // If parameter cannot be cast to Point return false.
            var p = obj as City;
            if ((System.Object)p == null)
            {
                return false;
            }
            return this.Equal((City) obj);
        }

        public bool Equal(City c)
        {
            // If parameter is null return false:
            if ((City)c == null)
            {
                return false;
            }

            // Return true if the fields match:
            return (this.Name.Equals(c.Name) && this.Country.Equals(c.Country));
        }
    }
}