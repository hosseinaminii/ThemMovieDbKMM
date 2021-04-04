//
//  MovieItemView.swift
//  iosApp
//
//  Created by Hossein on 4/3/21.
//  Copyright © 2021 orgName. All rights reserved.
//

import SwiftUI
import shared
import URLImage


struct MovieItemView: View {
    
    let movie: Movie
    
    var body: some View {
        
        ZStack(alignment:.bottom) {
            
            URLImage(url: URL(string: "https://image.tmdb.org/t/p/w342/\(movie.posterPath)")!) { image in
                image
                    .resizable()
            }
            
            HStack() {
                
                VStack(alignment:.leading) {
                    
                    Text(movie.title)
                        .font(.system(size: 20))
                        .font(.callout)
                        .foregroundColor(.white)
                        .lineLimit(1)
                    
                    Text(String(format: "%.1f/10", movie.voteAverage))
                        .font(.callout)
                        .foregroundColor(.white)
                        .lineLimit(1)
                
                    
                }
                Spacer()
            } .padding()
            .background(Color.black)
            
        }.frame(height: 300)
        
        
    }
}

struct MovieItemView_Previews: PreviewProvider {
    static var previews: some View {
        MovieItemView(movie: Movie(id: 100, title: "Hello Hossein Amini", releaseData: "20-10-2020", voteAverage: 4.3, overview: "Greate", posterPath: ""))
            .previewLayout(.fixed(width: 200, height: 300))
            
    }
}
