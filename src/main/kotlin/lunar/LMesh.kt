package lunar

import java.util.*

class LMesh {
    var vertices: ArrayList<LVector>
    var faces: ArrayList<LFace>
    var children: ArrayList<LMesh>

    constructor() {
        this.vertices = ArrayList()
        this.faces = ArrayList<LFace>()
        this.children = ArrayList()
    }

    constructor(vertices: ArrayList<LVector>, faces: ArrayList<LFace>) {
        this.vertices = vertices
        this.faces = faces
        this.children = ArrayList()
    }

    constructor(children: ArrayList<LMesh>) {
        this.vertices = ArrayList()
        this.faces = ArrayList<LFace>()
        this.children = ArrayList()
    }
}