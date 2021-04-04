import SwiftUI
import shared


//$(SRCROOT)/../shared/build/xcode-frameworks

func greet() -> String {
    return Greeting().greeting()
}

struct ContentView: View {
    
    @ObservedObject private(set) var viewModel: ViewModel
    
    var body: some View {
        
        NavigationView {
            listView().navigationTitle("The MovieDb")
        }
        
    }
    
    private func listView() -> AnyView {
        switch viewModel.state {
        case .loading:
            return AnyView(Text("Loading...").multilineTextAlignment(.center))
            
        case .success(let movies):
            return getGrid(movies: movies)
        case .failed:
            return AnyView(Text("Failed").multilineTextAlignment(.center))
        }
        
    }
    
    private func getGrid(movies: [Movie]) -> AnyView {
        let columnWidth = UIScreen.main.bounds.size.width / 2
        let columns = [GridItem(.fixed(columnWidth)), GridItem(.fixed(columnWidth))]
        return AnyView(
            ScrollView {
                LazyVGrid(columns: columns, spacing: 10) {
                    ForEach(movies, id: \.self) { movie in
                        MovieItemView(movie: movie)
                    }
                }
            }
        )
    }
    
}

extension ContentView {
    enum State {
        case loading
        case success([Movie])
        case failed
    }
    
    class ViewModel: ObservableObject {
        let api = Api()
        @Published var state = State.loading
        
        init() {
            self.api.getMovies(completionHandler: {result, error in
                if let result = result {
                    self.state = .success(result.movies)
                } else {
                    self.state = .failed
                }
            })
        }
        
    }
    
}
